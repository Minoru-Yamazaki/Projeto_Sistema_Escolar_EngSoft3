$(function () {
    
    var divContent = $('#campoIdMateria');
    var botaoAdd = $('a[data-id="2"]');
    var i = 2;

    //Ao clicar em adicionar ele cria uma linha com novos campos
    $(botaoAdd).click(function () {
        
        $('<div class="conteudoAdicional" id="conteudoAdicional"><div class="row mb-3"><div class="col-sm-2"><strong>Id Materia: </strong><input type="text" class="form-control" id="'+i+'" name="'+i+'"></div><div class="col-sm-1"><a href="#" class="linkRemover"><br>Remover</a></div></div></div>').appendTo(divContent);
        $('#removehidden').remove();
        document.getElementById("txtQtde").value = ++i - 1;
        $('<input type="hidden" name="quantidadeCampos" value="'+ i +'" id="removehidden">').appendTo(divContent);
    });

    //Cliquando em remover a linha é eliminada
    $('#campoIdMateria').on('click', '.linkRemover', function () {
        $(this).parents('.conteudoAdicional').remove();
        document.getElementById("txtQtde").value = --i - 1;
    });
});