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
				//console.log(json);
				//var texto = "El id es: " + conf.id + " y el nombre es: " + conf.nombre;
				//console.log(texto);
			});
			
		}
	});

	$('#conferencias').on('change', function(){
		var id = this.value;
		console.log(id);
		var nombre = $('#conferencias option:selected').text();
		console.log(nombre);
		$.ajax({
			type: "POST",
			url: "asistentesController",
			data: {"idConferencia" : this.value}, 
			success: function(json){
				console.log(json);
			}
		});




	});


});
