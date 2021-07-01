Date.prototype.addDays = function (days) {
  var date = new Date(this.valueOf());
  date.setDate(date.getDate() + days);
  return date;
}

$(document).ready(function () {

  console.log(contrato);
  if (empleado === null) {
    document.getElementById("botonCrear").disabled = true;
    document.getElementById("botonEditar").disabled = true;
    document.getElementById("botonEliminar").disabled = true;
    console.log(contrato);
  }
  else {
    
    if (contrato.id === null) {
      document.getElementById("botonCrear").disabled = false;
      document.getElementById("botonEditar").disabled = true;
      document.getElementById("botonEliminar").disabled = true;
    }
    else if(contrato.id != null) {
      document.getElementById("botonCrear").disabled = true;
      document.getElementById("botonEditar").disabled = false;
      document.getElementById("botonEliminar").disabled = false;
    }
  }

  $('.editarContrato, .agregarContrato').on('click', function () {

    let text = $(this).text();
    const header = document.getElementById("modalHeader");

    if (text.includes("Editar")) {

      document.getElementById("id").readOnly = true;
      document.getElementById("id").style.pointerEvents = "none";
      header.classList.remove("bg-info");
      header.classList.add("bg-warning");
      $('.my-form #id').val(contrato.id);
      $('.my-form #horasContratadasPorSemana').val(contrato.horasContratadasPorSemana);
      $('.my-form #valorHora').val(contrato.valorHora);
      $('.my-form #fechaInicio').val(contrato.fechaInicio);
      $('.my-form #fechaFin').val(contrato.fechaFin);
      if (contrato.asignacionFamiliar) {
        document.getElementById("activado").checked = true;
        document.getElementById("desactivado").checked = false;
      }
      $('.my-form #asignacionFamiliar').val(contrato.asignacionFamiliar);
      $('.my-form #afp').val(contrato.afp.id);

      $('.my-form #descripcionVentana').text("Editar contrato"); 

    }
    else {
      document.getElementById("id").readOnly = true;
      document.getElementById("id").style.pointerEvents = "none";
      header.classList.remove("bg-warning");
      header.classList.add("bg-info");
      $('.my-form #id').val("");
      $('.my-form #horasContratadasPorSemana').val("8");
      $('.my-form #valorHora').val("10");
      $('.my-form #fechaInicio').val("");
      $('.my-form #fechaFin').val("");
      $('.my-form #asignacionFamiliar').val("");
      $('.my-form #afp').val("");
      $('.my-form #empleado_id').val(empleado.id);

      $('.my-form #descripcionVentana').text("Agregar contrato");

    }

    $('.my-form #agregarContratoModal').modal({ show: true });

    return false;

  });

  $('.eliminarContrato').on('click', function () {

    const link = "/contrato/eliminar/" + contrato.id;
    $('#eliminarContratoModal #eliminar').attr('href', link);
    $('#eliminarContratoModal').modal({ show: true });
  });

});

function guardado(){
  let header = document.getElementById("modalHeader");
  let accion = header.textContent;
  console.log(accion)
  if(accion.includes("Editar")){
    alert("Se guardó las modificaciones del contrato.");
  }
  else{
    alert("Se guardó el nuevo contrato.");
  }
  
}

function setFechaFin(seleccionado){
  let fechaInicio = seleccionado.valueAsDate;
  fechaInicio = fechaInicio.addDays(93);
  let dd = String(fechaInicio.getDate()).padStart(2, '0');
  let MM = String(fechaInicio.getMonth() + 1).padStart(2, '0'); //Enero es 0
  let yyyy = fechaInicio.getFullYear();

  let minFechaFin = new Date();

  minFechaFin = yyyy + '-' + MM + '-' + (dd);

  fechaInicio = seleccionado.valueAsDate;
  fechaInicio = fechaInicio.addDays(365);
  dd = String(fechaInicio.getDate()).padStart(2, '0');
  MM = String(fechaInicio.getMonth() + 1).padStart(2, '0'); //Enero es 0
  yyyy = fechaInicio.getFullYear();

  let maxFechaFin = new Date();

  maxFechaFin = yyyy + '-' + MM + '-' + (dd);

  $('.my-form #fechaFin').attr('min',minFechaFin);
  $('.my-form #fechaFin').attr('max',maxFechaFin);
}