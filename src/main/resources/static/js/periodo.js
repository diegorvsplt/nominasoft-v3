$(document).ready(function () {

  $('.botonEditar, .botonAgregar').on('click', function (event) {
    event.preventDefault();
    let href = $(this).attr('href');
    let text = $(this).text();
    const header = document.getElementById("modalHeader");

    if (text.includes("Editar")) {
      $.get(href, function (periodo, status) {
        header.classList.remove("bg-info");
        header.classList.add("bg-warning");

        $('.my-form #id').val(periodo.id);
        $('.my-form #estado').val(periodo.estado);
        $('.my-form #fechaInicio').val(periodo.fechaInicio);
        $('.my-form #fechaFin').val(periodo.fechaFin);
        
        $('.my-form #nombreVentana').text("Editar periodo");
      })
    }
    else {

      header.classList.remove("bg-warning");
      header.classList.add("bg-info");
      $('.my-form #id').val("");
      $('.my-form #fechaInicio').val("");
      $('.my-form #fechaFin').val("");
      

      $('.my-form #nombreVentana').text("Agregar periodo");
    }

    $('.my-form #agregarPeriodoModal').modal({ show: true });

    return false;

  });

  $('.botonEliminar').on('click', function (event) {
    event.preventDefault();
    let href = $(this).attr('href')
    $('#eliminarPeriodoModal #eliminar').attr('href', href);
    $('#eliminarPeriodoModal').modal({ show: true });
  });

});
