Date.prototype.addDays = function (days) {
  var date = new Date(this.valueOf());
  date.setDate(date.getDate() + days);
  return date;
}

$(document).ready(function () {

  let procesarForm = document.getElementById('formProcesar');
  procesarForm.addEventListener('submit', (event) =>{
    if (periodo == null) {
      event.preventDefault();
      alert("No se puede procesar porque no existe un periodo de pago activo.")
    }
    else {
      let fechaActual = new Date();
      let fechaFin = new Date(periodo.fechaFin);
      fechaActual.setHours(0, 0, 0, 0);
      fechaFin = fechaFin.addDays(1);
      fechaFin.setHours(0, 0, 0, 0);

      if (fechaActual.getTime() != fechaFin.getTime()) {
        event.preventDefault();
        alert("No se puede procesar el periodo porque la fecha actual debe ser mayor o igual a la fecha fin del periodo de pago");

      }
      else if(contratos.length == 0){
        console.log(contratos);
        event.preventDefault();
        alert("No se puede procesar el periodo porque porque no existen contratos");
      } 
      else{
        procesarForm.submit();
      }
    }
  });

});
