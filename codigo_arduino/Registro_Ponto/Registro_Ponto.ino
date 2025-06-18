#include <WiFi.h>
#include <HTTPClient.h>
#include <LiquidCrystal_I2C.h>
#include <Wire.h>
#include <SPI.h>
#include <MFRC522.h>
#include <ArduinoJson.h>
#include <time.h>

#define SS_PIN 5
#define RST_PIN 4
#define BUZZER_PIN 14

const char* ssid = "Michel";
const char* password = "michel070";
const String baseURL = "http://192.168.176.54:8080/api/historico";

LiquidCrystal_I2C lcd(0x27, 16, 2);
MFRC522 mfrc522(SS_PIN, RST_PIN);

void buzzerBeep(int duration) {
  digitalWrite(BUZZER_PIN, HIGH);
  delay(duration);
  digitalWrite(BUZZER_PIN, LOW);
}

void setup() {
  Serial.begin(115200);
  pinMode(BUZZER_PIN, OUTPUT);
  digitalWrite(BUZZER_PIN, LOW);

  WiFi.begin(ssid, password);
  lcd.init();
  lcd.backlight();

  lcd.setCursor(0, 0);
  lcd.print("Conectando...");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  lcd.clear();
  lcd.print("Conectado!");

  SPI.begin();
  mfrc522.PCD_Init();

  configTime(-10800, 0, "pool.ntp.org", "time.nist.gov");
  lcd.setCursor(0, 1);
  lcd.print("Esperando RFID");
}

void registrarPonto(String codigoRFID) {
  if (WiFi.status() != WL_CONNECTED) {
    lcd.clear();
    lcd.print("Erro WiFi");
    buzzerBeep(200);
    return;
  }

  struct tm timeinfo;
  if (!getLocalTime(&timeinfo)) {
    lcd.clear();
    lcd.print("Erro hora");
    buzzerBeep(200);
    return;
  }

  char dataStr[11];
  char horaStr[9];
  strftime(dataStr, sizeof(dataStr), "%Y-%m-%d", &timeinfo);
  strftime(horaStr, sizeof(horaStr), "%H:%M:%S", &timeinfo);

  StaticJsonDocument<512> doc;
  doc["dataderegistro"] = dataStr;
  doc["horario"] = horaStr;
  JsonObject funcionario = doc.createNestedObject("funcionario");
  funcionario["rfiduid"] = codigoRFID;

  String json;
  serializeJson(doc, json);

  HTTPClient http;
  http.begin(baseURL + "/registrar");
  http.addHeader("Content-Type", "application/json");

  int code = http.POST(json);

  if (code == 200 || code == 201) {
    Serial.println("Ponto registrado!");
    lcd.clear();
    lcd.print("Ponto OK");
    buzzerBeep(500);
  } else {
    Serial.print("Erro ponto: ");
    Serial.println(code);
    lcd.clear();
    lcd.print("Erro ponto");
    buzzerBeep(1000);
  }

  http.end();
}

void loop() {
  if (!mfrc522.PICC_IsNewCardPresent() || !mfrc522.PICC_ReadCardSerial()) {
    return;
  }

String tempUID = "";
for (byte i = 0; i < mfrc522.uid.size; i++) {
  if (mfrc522.uid.uidByte[i] < 0x10) tempUID += "0";
  tempUID += String(mfrc522.uid.uidByte[i], HEX);
}
tempUID.toUpperCase();
String rfidUID = tempUID;


  Serial.println("RFID: " + rfidUID);

  lcd.clear();
  lcd.print("Registrando...");

  registrarPonto(rfidUID);
  delay(3000);

  lcd.clear();
  lcd.print("Aproxime o cartao");
  mfrc522.PICC_HaltA();
  mfrc522.PCD_StopCrypto1();
}
