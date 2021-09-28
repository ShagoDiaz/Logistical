$(document).ready(function() {
	var modalEliminar = document.getElementById('delModal');
	var modalEdit = document.getElementById('editModal');
	var items = document.getElementById('items');
	var $form = $('#filtro');

	modalEliminar
		.addEventListener(
			'show.bs.modal',
			function(event) {
				var button = event.relatedTarget;
				var recipient = button.getAttribute('data-bs-id');
				modalEliminar.querySelector('#id_product').value = recipient;
				modalEliminar.querySelector('#texto').textContent = '¿Esta seguro de eliminar el producto '
					+ recipient + '?';

			})

	modalEdit
		.addEventListener(
			'show.bs.modal',
			function(event) {

				var button = event.relatedTarget;
				var recipient = button.getAttribute('data-bs-id');
				var datos = recipient.substring(8, recipient.length - 1).split(',');
				var valores = [];
				for (let i = 0; i < datos.length; i++) {
					let aux = datos[i].split('=');
					valores[i] = aux[1];
				}

				modalEdit.querySelector('#id_product').value = valores[0];
				modalEdit.querySelector('#name').value = valores[1];
				modalEdit.querySelector('#price').value = valores[2];
				modalEdit.querySelector('#stock').value = valores[3];

			})

	$("#items").on("change", function() {
		window.location.href = "/logistiqal/items?items=" + $(this).val();
	});

	$form.submit(function(event) {
		event.preventDefault();
		var text = document.getElementById("texto");
		window.location.href = "/logistiqal/?text=" + text.value + "";
	});

});