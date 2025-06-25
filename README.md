# 🔐 Sistema de Ponto RFID com ESP32, Java e PostgreSQL

![Plataforma](https://img.shields.io/badge/Plataforma-Arduino%20%2B%20ESP32-blue)
![Backend](https://img.shields.io/badge/Backend-Java%20%2F%20SpringBoot-green)
![Banco de Dados](https://img.shields.io/badge/Database-PostgreSQL-blueviolet)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

> Projeto acadêmico de sistema de registro de ponto automatizado, utilizando ESP32 com RFID para identificação, e backend Java com banco de dados PostgreSQL para persistência dos dados.

---

## 📷 Visão Geral

Este projeto tem como objetivo desenvolver um sistema de ponto eletrônico baseado em tecnologia RFID. O ESP32 realiza a leitura da TAG, conecta-se via WiFi e envia os dados de ponto (entrada/saída) para uma API Java que registra os dados em um banco PostgreSQL.

**Componentes utilizados:**
- ESP32
- Módulo Leitor RFID RC522
- LCD I2C 16x2
- Buzzer passivo
- Java + Spring Boot (REST API)
- PostgreSQL


---

## 🧠 Funcionalidade

📌 Fluxo do sistema:

1. O funcionário aproxima sua TAG RFID do leitor.
2. O ESP32 lê o UID da TAG.
3. Os dados são enviados via HTTP para a API REST Java.
4. O backend verifica se é entrada ou saída e grava o registro com data/hora no banco.
5. O display LCD exibe o status da operação.
6. Um beep de confirmação é emitido via buzzer.

---

## 🔌 Esquema de Ligações (RC522 + ESP32)

| RC522    | ESP32     |
|----------|-----------|
| SDA      | GPIO 5    |
| SCK      | GPIO 18   |
| MOSI     | GPIO 23   |
| MISO     | GPIO 19   |
| GND      | GND       |
| RST      | GPIO 4    |
| 3.3V     | 3.3V      |

![Esquema de Montagem](imagens/esquema_ligacoes.png)

---

## 📂 Estrutura do Projeto

```
Sistema_Arduino/
├── sketch/                 # Códigos Arduino
│   └── main.ino            # Lógica principal do ESP32
├── imagens/                # Imagens de teste e demonstração
├── backend/                # (Opcional - API Java/Spring se estiver no mesmo repositório)
└── README.md               # Este arquivo
```

---

## ⚙️ Como Executar

### ESP32 (Arduino)

1. Abra o arquivo `main.ino` no Arduino IDE.
2. Instale as bibliotecas:
   - `MFRC522`
   - `WiFi`
   - `HTTPClient`
   - `LiquidCrystal_I2C`
   - `ArduinoJson`
3. Configure o WiFi e o endpoint da API no código:
   ```cpp
   const char* ssid = "SEU_WIFI";
   const char* password = "SENHA_WIFI";
   const char* serverName = "http://IP_BACKEND:PORTA/registro";
   ```
4. Conecte o ESP32 e faça upload do código.
5. Abra o Monitor Serial (115200) para acompanhar logs.

---

## 🧪 Exemplo de Resposta no LCD

```text
TAG LIDA: 73938FEC
PONTO REGISTRADO!
```

---

## 🔐 Backend (Java + Spring Boot)

Caso esteja usando a API desenvolvida junto, ela deve:

- Ter endpoint `POST /registro`
- Aceitar JSON com UID e tipo (entrada/saida)
- Persistir os dados no PostgreSQL com data/hora

---

## 🎯 Futuras Melhorias

- [ ] Implementar login e autenticação no backend
- [ ] Dashboard com relatórios de ponto
- [ ] Cadastro automático de TAGs novas via interface web
- [ ] Melhor organização e modularização do código Arduino
- [ ] Controle de turnos e horas extras

---

## 👨‍💻 Desenvolvedores

| Nome            | Função                |
|-----------------|------------------------|
| Michel Germano  | Código Arduino & Backend |
| Diego (exemplo) | Apoio & Testes        |

---

## 🪪 Licença

Este projeto é de uso educacional e livre para estudo. Para uso comercial, entre em contato com os desenvolvedores.

---

## 🌐 Contato

📧 michelgermano010@outlook.com  
🐙 GitHub: [MisheruSama](https://github.com/MisheruSama)
