/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
json = [conf1 = {"id" : "1", "nombre" : "conferencia1" }, ]
*/
$(document).ready(function(){
	$.ajax({
		type: "POST",
		url: "conferencias",
		success: function(json){
			//json es el arreglo de conferencias
			//console.log(json);
			var arrayConferencias = json.conferencias;
			//console.log(arrayConferencias);
			$.each(arrayConferencias, function(i, conf){
				var option = $('<option/>').val(conf.id).html(conf.nombre);
				$('#conferencias').append(option);
                                //$('#inputConferenciaId').append(option);
				//console.log(json);
				//var texto = "El id es: " + conf.id + " y el nombre es: " + conf.nombre;
				//console.log(texto);
			});
			
		}
	});
        
        $.ajax({
		type: "POST",
		url: "conferencias",
		success: function(json){
			//json es el arreglo de conferencias
			//console.log(json);
			var arrayConferencias = json.conferencias;
			//console.log(arrayConferencias);
			$.each(arrayConferencias, function(i, conf){
				var option = $('<option/>').val(conf.id).html(conf.nombre);
				$('#inputConferenciaId').append(option);
                                //$('#inputConferenciaId').append(option);
				//console.log(json);
				//var texto = "El id es: " + conf.id + " y el nombre es: " + conf.nombre;
				//console.log(texto);
			});
			
		}
	});

	$('#conferencias').on('change', function(){
		$('#tbody').empty();
		var id = this.value;
		console.log(id);
		var nombre = $('#conferencias option:selected').text();
		console.log(nombre);
		$.ajax({
			type: "POST",
			url: "asistentesController",
			data: {"idConferencia" : this.value}, 
			success: function(json){
				//console.log(json);
				$.each(json.asistentes, function(idex, asistente){
					console.log(asistente);
					var container = $('<div>', {class: 'modal fade', id: asistente.cedula, tabindex: '-1', role: 'dialog'});
                                        var modalDialog = $('<div>', {class: 'modal-dialog modal-sm', role: 'document'});
                                        var modalContent = $('<div>', {class: 'modal-content'});
                                        var form = $('<form>', {action: 'AsistentesServlet', method: 'post'});
                                        var modalBody = $('<div>', {class: 'modal-body'});
                                        var msg = $('<p>').html('Seguro que desea eliminar al asistente: ' + asistente.nombre);
                                        var value = $('<input>', {type: 'hidden', name: 'id', value: asistente.cedula});
                                        var modalFooter = $('<div>', {class: 'modal-footer'});
                                        modalFooter.html("<button class='btn btn-danger' type='submit' name='id' >OK</button><button class='btn' data-dismiss='modal'>Cancelar</button>");
                                        modalBody.append(msg).append(value);
                                        form.append(modalBody).append(modalFooter);
                                        modalContent.append(form);
                                        modalDialog.append(modalContent);
                                        container.append(modalDialog);
                                        $('#pageContent').append(container);
                                        
					var trow = $('<tr/>');
					var tdCedula = $('<td/>').text(asistente.cedula);
					var tdNombre = $('<td/>').text(asistente.nombre);
					var tdApellido = $('<td/>').text(asistente.apellido);
					var tdCorreo = $('<td/>').text(asistente.correo);
					
					//MODIFICAR ESTO PARA LA FUNCION EDITAR
					var aEditar = $('<a/>').attr({"href" : "#"});
					var spanEditar = $('<span/>').attr({
						"class" : "glyphicon glyphicon-edit",
						"aria-hidden" : "true"
						//ANADIR AQUI LO QUE HACE FALTA PARA CREAR EL MODAL DE EDITAR
					})
					var tdEditar = $('<td/>');
					aEditar.append(spanEditar);
					tdEditar.append(aEditar);

					//MODIFICAR ESTO PARA LA FUNCION ELIMINAR
					var aEliminar = $('<a/>').attr({
                                                "href" : "#",
                                                "data-toggle": "modal",
                                                "data-target": "#" + asistente.cedula
                                        });
					var spanEliminar = $('<span/>').attr({
						"class" : "glyphicon glyphicon-remove",
						"aria-hidden" : "true"
					});
					var tdEliminar = $('<td/>');
					aEliminar.append(spanEliminar);
					tdEliminar.append(aEliminar);

					trow.append(tdCedula, tdNombre, tdApellido, tdCorreo, tdEditar, tdEliminar);
					$('#tbody').append(trow);
				})
			}
		});
	});
});

$("#asistantFormSubmit").on("click", function(){
    $.post("CreateAsistant",
    {
        inputAsistantId: $("#asistantForm #inputAsistantId").val(),
        inputAsistantName: $("#asistantForm #inputAsistantName").val(),
        inputAsistantLast: $("#asistantForm #inputAsistantLast").val(),
        inputAsistantEmail: $("#asistantForm #inputAsistantEmail").val(),
        inputConferenciaId: $("#asistantForm #inputConferenciaId").val()
    },
    function(data, status){
        if(status === "success"){
            $("#asistantForm #inputAsistantId").val("");
            $("#asistantForm #inputAsistantName").val("");
            $("#asistantForm #inputAsistantLast").val("");
            $("#asistantForm #inputAsistantEmail").val("");
            $("#asistantForm #inputConferenciaId").val("");
            $("#modalAsistentes").modal('hide');
            var alertContainer = ($("<div>", {class: "alert alert-success alert-dismissible fade in", role: "alert"}));
            alertContainer.html('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>Asistente guardado con éxito');
            $("#pageContent").append(alertContainer);
        }else{
            alert("Hubo un problema al momento de guardar");
        }
    });
});
