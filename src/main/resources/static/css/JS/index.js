const apiUrl = '/api/funcionarios';

async function listarFuncionarios() {
    const res = await fetch(apiUrl);
    if (!res.ok) throw new Error('Erro ao listar funcionários');
    return await res.json();
}

async function adicionarFuncionario(funcionario) {
    const res = await fetch(`${apiUrl}/cadastrar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(funcionario)
    });
    if (!res.ok) throw new Error('Erro ao adicionar funcionário');
    return await res.json();
}

async function editarFuncionario(id, funcionario) {
    const res = await fetch(`${apiUrl}/editar/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(funcionario)
    });
    if (!res.ok) throw new Error('Erro ao editar funcionário');
    return await res.json();
}

async function excluirFuncionario(id) {
    const res = await fetch(`${apiUrl}/deletar/${id}`, {
        method: 'DELETE'
    });
    if (!res.ok) throw new Error('Erro ao excluir funcionário');
    return true;
}
