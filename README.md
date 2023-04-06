<h1>Voll.MeD API</h1>


API rest para gerenciar consultas de uma clinica medica. A aplicação possui funcionalidades que permitem o cadastro de médicos e de pacientes, e também o agendamento e cancelamento de consultas.

## As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:
* Java 17
* Spring Boot 3
* Maven
* MySQL
* Hibernate
* Flyway
* Lombok



<h3>Funcionalidades</h3>
    - `Cadastro de médicos e pacientes: `       
        Medico: Nome, E-mail, Telefone, CRM, Especialidade (Ortopedia, Cardiologia, Ginecologia ou Dermatologia).
        Pacientes:  Nome, E-mail, CPF, Telefone.
        Endereço completo (logradouro, número, complemento, bairro, cidade, UF e CEP)
        Todas as informações são de preenchimento obrigatório, exceto o número e o complemento do endereço.

    - `Listar médicos e pacientes`, no qual as seguintes informações devem ser exibidas:
        Medico: Nome, E-mail, CRm, Especialidade.
        Pacientes:  Nome, E-mail, CPF.
        A listagem deve ser ordenada pelo nome, de maneira crescente, bem como ser paginada, trazendo 10 registros.

    - `Atualização de dados cadastrais` de médicos e paciente, na qual as seguintes informações poderão ser atualizadas:
        Medico: Nome, Telefone, endereço
        Pacientes:  Nome, telefone, endereço.
        As seguintes regras de negócio devem ser validadas pelo sistema:
            Não é permitido a alteração do e-mail, CRM e especialidade do médico;
            Não é permitido a alteração do e-mail, CPF  do paciente;
    - `exclusão de cadastrados`
        As seguintes regras de negócio devem ser validadas pelo sistema:
            A exclusão não deve apagar os dados do paciente ou médico, mas torná-lo como "inativo" no sistema. 

    #Agendamento de consultas(em breve);
    #Cancelamento de consultas(em breve);



