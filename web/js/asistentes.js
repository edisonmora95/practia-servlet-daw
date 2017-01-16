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
                                $('#inputConferenciaId').append(option);
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
					var aEliminar = $('<a/>').attr({"href" : "#"});
					var spanEliminar = $('<span/>').attr({
						"class" : "glyphicon glyphicon-remove",
						"aria-hidden" : "true"
						//ANADIR AQUI LO QUE HACE FALTA PARA CREAR EL MODAL DE ELIMINAR
					})
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
