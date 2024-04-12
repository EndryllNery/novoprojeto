document.getElementById('clienteForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita o envio do formulário padrão

    // Obter os valores dos campos do formulário
    var nome = document.getElementById('nome').value;
    var cpf = document.getElementById('cpf').value;
    var idade = document.getElementById('idade').value;

    //  envio dos dados para o backend Java usando AJAX ou Fetch API
    // enviarDadosParaBackend(nome, cpf, idade);

    // Limpar os campos do formulário após o envio
    document.getElementById('nome').value = '';
    document.getElementById('cpf').value = '';
    document.getElementById('idade').value = '';

    // Atualizar a lista de clientes (se necessário)
    // Exemplo: listarClientes();
