$(document).ready(function () {

  $('.editarEmpleado, .agregarEmpleado').on('click', function (event) {
    event.preventDefault();
    let href = $(this).attr('href');
    let text = $(this).text();
    const header = document.getElementById("modalHeader");

    if (text.includes("Editar")) {
      $.get(href, function (empleado, status) {
        header.classList.remove("bg-info");
        header.classList.add("bg-warning");
        document.getElementById("dni").readOnly = true;
        document.getElementById("dni").style.pointerEvents = "none";
        $('.my-form #id').val(empleado.id);
        $('.my-form #dni').val(empleado.dni);
        $('.my-form #nombre').val(empleado.nombre);
        $('.my-form #gradoAcademico').val(empleado.gradoAcademico);
        $('.my-form #estadoCivil').val(empleado.estadoCivil);
        $('.my-form #telefono').val(empleado.telefono);
        $('.my-form #direccion').val(empleado.direccion);
        $('.my-form #fechaNacimiento').val(empleado.fechaNacimiento);
        $('.my-form #nombreVentana').text("Editar empleado");
      })
    }
    else {
      document.getElementById("dni").readOnly = false;
      document.getElementById("dni").style.pointerEvents = "auto";
      header.classList.remove("bg-warning");
      header.classList.add("bg-info");
      $('.my-form #id').val("");
      $('.my-form #dni').val("");
      $('.my-form #nombre').val("");
      $('.my-form #gradoAcademico').val("");
      $('.my-form #estadoCivil').val("");
      $('.my-form #telefono').val("");
      $('.my-form #direccion').val("");
      $('.my-form #fechaNacimiento').val("");
      $('.my-form #nombreVentana').text("Agregar empleado");
    }

    $('.my-form #agregarEmpleadoModal').modal({ show: true });

    return false;

  });

  $('.botonEliminar').on('click', function (event) {
    event.preventDefault();
    let href = $(this).attr('href')
    $('#eliminarEmpleadoModal #eliminar').attr('href', href);
    $('#eliminarEmpleadoModal').modal({ show: true });
  });

  $('.botonMostrarContratos').on('click', function (event) {
    event.preventDefault();
    let tbody = document.getElementById("tbodyContratos");
    $.get(href, function (listaContratos, status) {
      for (let contrato of listaContratos) {
        let row = tbody.insertRow();
        for (key in contrato) {
          if (key != "afp" && key != "empleado") {

          }
        }
      }
    })
    $('#mostrarContratosModal').modal({ show: true });
  });

});
