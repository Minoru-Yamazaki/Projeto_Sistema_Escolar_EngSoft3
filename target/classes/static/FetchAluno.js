function findById(idParametro){
	id = document.getElementById('idAluno').value;
	if(idParametro != null){
		id = idParametro;
	}
                   
    var html = "<br><h2 class='text-center'>MATÉRIAS DO ALUNO</h2><div><table class='table table-striped'><thead class='thead-dark'><tr><th> id </th><th>Nome</th><th>Sigla</th><th>Carga Horária</th><th></th></tr></thead>"; 
    
	fetch('http://localhost:8080/aluno/' + id)
    .then(response => response.json())
    .then(data => {        
        if(data.id != null){
            document.getElementById("txtNome").value = data.nome;
            document.getElementById("txtEmail").value = data.email;
            document.getElementById("txtIdade").value = data.idade;
            document.getElementById("txtRA").value = data.ra;
            document.getElementById("txtTelefone").value = data.telefone;
            			
			for (x in data.materias) {
                html += "<tr><td id='" + x +"'>" + data.materias[x].id + "</td><td>" + data.materias[x].nome + "</td><td>" +
                data.materias[x].sigla + "</td><td>" + data.materias[x].cargaHoraria + "</td><td>  <button type='button' id='btnAlterar' onclick='teste(" + x + ")' class='btn btn-danger'>Excluir</button> </td></tr>";
            }
            html += "</table> </div>"    
            document.getElementById("tabelaListar").innerHTML = html;
			console.log(data)
        }else{
			alert("ID inválido!");
		}
    }); 
}  

function pegarValorTabela(){
    var ids = [];
    var qtdeIds = 1;
	qtdeIds = document.getElementById("txtQtde").value;
	for (var i = 0; i < qtdeIds; i++) {
		ids.push(document.getElementById(i+1).value);
    }
    alert(ids);
}

function teste(id){
    valor = document.getElementById(id).innerHTML;
    alert(valor);
}

function listar(){
    var txt = ""; 
    fetch('http://localhost:8080/contacts/')
    .then(response => response.json()) //  <th> </th>
    .then(data => {

        txt += "<br><div class='container'><table class='table table-striped'> <thead class='thead-dark'><tr><th> Registro </th><th>Nome</th><th>Email</th><th>Celular</th></tr></thead>"
        for (x in data) {
            txt += "<tr><td>" + data[x].id + "</td><td>" + data[x].name + 
                  "</td><td>" + data[x].email + "</td><td>" + data[x].phone + "</td></tr>";
        }
        txt += "</table> </div>"    
        document.getElementById("tabelaListar").innerHTML = txt;

        console.log(data)
    });         
    limpar();
    document.getElementById("btnAlterar").disabled = true; 
    document.getElementById("btnExcluir").disabled = true; 
}  

function deleteById(){
    id = document.getElementById('txtId').value;

    fetch('http://localhost:8080/contacts/' + id, {
        method: 'DELETE',
    })
    .then(response => response.json()) // or res.text()
    .then(res => console.log(res));

    document.getElementById("tabelaListar").innerHTML = "";
    document.getElementById("btnAlterar").disabled = true; 
    document.getElementById("btnExcluir").disabled = true; 
         
}

function inserir(){
    if(verificaPreenchimento()){
        const someData = {
            name: document.getElementById('txtNome').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('celular').value
        }
    
        const postMethod = {
            method: 'POST', // Method itself
            headers: {
             'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
            },
            body: JSON.stringify(someData) // We send data in JSON format
           }
    
        fetch('http://localhost:8080/contacts', postMethod)
        .then(response => response.json())
        .then(data => console.log(data)) // Manipulate the data retrieved back, if we want to do something with it
        .catch(err => console.log(err))
    }else{
        alert("Preenchimento incorreto");
    }
    document.getElementById("tabelaListar").innerHTML = "";
    document.getElementById("btnAlterar").disabled = true; 
    document.getElementById("btnExcluir").disabled = true;  
    limpar();   
}

function alterar(){
    id = document.getElementById('txtId').value;

    const someData = {
        name: document.getElementById('txtNome').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('celular').value
    }

    const putMethod = {
        method: 'PUT', // Method itself
        headers: {
         'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
        },
        body: JSON.stringify(someData) // We send data in JSON format
       }

    fetch('http://localhost:8080/contacts/' + id, putMethod)
    .then(response => response.json())
    .then(data => console.log(data)) // Manipulate the data retrieved back, if we want to do something with it
    .catch(err => console.log(err))

    document.getElementById("tabelaListar").innerHTML = "";
    document.getElementById("btnAlterar").disabled = true; 
    document.getElementById("btnExcluir").disabled = true;       
}

function limpar(){
    document.getElementById("txtId").value = "";
    document.getElementById("txtNome").value = "";
    document.getElementById("email").value = "";
    document.getElementById("celular").value = "";
}

function verificaPreenchimento(){
    var name = document.getElementById("txtNome").value;
    var email = document.getElementById("email").value;
    var phone = document.getElementById("celular").value;

    if(name == "" ||  email == "" || phone == ""){
        return false;
    } else{
        return true;
    }
}