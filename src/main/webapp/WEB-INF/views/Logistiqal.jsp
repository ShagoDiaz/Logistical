<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/bb6a9a313e.js"
	crossorigin="anonymous"></script>
<link href="/css/style.css" rel="stylesheet" />
<title>Logistical</title>
</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 px-5 py-5">
				<div class="row"></div>
				<div class="row">
					<div class="col py-3">
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#createModal">Registrar
							Producto</button>
					</div>
				</div>
				<div class="row">
					<form:form id="filtro">
						<div class="col py-3">
							<input class="form-control" type="text"
								placeholder="¿Que buscas hoy?" aria-label="texto" name="texto"
								id="texto" required />
						</div>
						<div class="col py-3">
							<input type="submit" class="btn btn-outline-primary"
								value="Filtrar">
						</div>
					</form:form>
				</div>
				<div class="row">
					<div class="col py-3">
						<select class="form-select" aria-label="Default select example"
							id="items">
							<option selected disabled>Resultados por pagina</option>
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="50">50</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col py-5" id="content">
				<div class="row"></div>
				<div class="row px-5">
					<table class="table table-light table-striped table-hover">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Name</th>
								<th scope="col">Price</th>
								<th scope="col">Stock</th>
								<th scope="col" colspan="2">Options</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${products}" var="product" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${product.name}</td>
									<td>$${product.price}</td>
									<td>${product.stock}</td>
									<td><span style="color: Blue;"> <i
											class="far fa-edit fa-2x" data-bs-toggle="modal"
											data-bs-id="${product}" data-bs-target="#editModal"></i></span></td>
									<td><span style="color: Red;"> <i
											class="far fa-trash-alt fa-2x" data-bs-toggle="modal"
											data-bs-id="${product.id_product}" data-bs-target="#delModal"></i></span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<c:forEach varStatus="i" begin="1" end="${pages}">
								<li class="page-item"><a class="page-link"
									href="/logistiqal/page?page=${i.count-1}">${i.count}</a></li>
							</c:forEach>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal nuevo registro -->
	<div class="modal fade" id="createModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="add" method="post" class="row g-3 needs-validation"
					validate>
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Register new
							product</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="row py-2">
							<div class="col">
								<input type="hidden" class="form-control" name="id_product"
									id="id_product"> <input type="text"
									class="form-control" placeholder="Name" aria-label="name"
									name="name" id="name" required>
							</div>
							<div class="col">
								<input type="number" class="form-control" placeholder="Price"
									aria-label="price" name="price" id="price" required>
							</div>
						</div>
						<div class="row  py-2">
							<div class="col">
								<input type="number" class="form-control" placeholder="Stock"
									aria-label="stock" name="stock" id="stock" required>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Modal edit registro -->
	<div class="modal fade" id="editModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="edit" method="post" class="row g-3 needs-validation"
					validate>
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Update product</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="row py-2">
							<div class="col">
								<input type="hidden" class="form-control" name="id_product"
									id="id_product" value=""> <input type="text"
									class="form-control" placeholder="Name" aria-label="name"
									name="name" id="name" value="" required>
							</div>
							<div class="col">
								<input type="number" class="form-control" placeholder="Price"
									aria-label="price" name="price" id="price" value="" required>
							</div>
						</div>
						<div class="row  py-2">
							<div class="col">
								<input type="number" class="form-control" placeholder="Stock"
									aria-label="stock" name="stock" id="stock" value="" required>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Modal eliminar registro -->
	<div class="modal fade" id="delModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="del" method="post" class="row g-3 needs-validation"
					validate>
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Eliminar
							producto</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<input type="hidden" name="id_product" id="id_product" value="" />
						<label id="texto"> </label>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-outline-danger">Eliminar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
		integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
		integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="/js/script.js"></script>
</body>

</html>