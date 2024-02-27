import Swal from 'sweetalert2';

export function fireSwal(
  message: any,
  type: any,
  time: any,
  posicion: any = 'top'
) {
  Swal.fire({
    title: message,
    icon: type,
    timer: time,
    timerProgressBar: true,
    showConfirmButton: false,
    position: posicion,
  });
}

export function confirmSwal(message: any, type: any) {
  return Swal.fire({
    title: message,
    icon: type,
    showConfirmButton: true,
    showDenyButton: true,
  });
}
