$(function() {
    // Al hacer click sobre uno de los botones (ver, editar, borrar)
    $('[data-do]').on('click', function(event) {
        // Prevenir acción por defecto
        event.preventDefault();

        // Recuperar ID del Accidente seleccionado
        let id = $(this).data('id');

        // Recuperar la acción a realizar
        let accion = $(this).data('do');

        // Confirmar eliminación del registro
        if(accion === 'borrar') {
            if(confirm('¿Está seguro de querer eliminar el registro del accidente?')) {
                $(location).attr('href', '?do=borrar&id=' + id);
            } else {
                return;
            }
        }

        $(location).attr('href', '?do=' + accion + (id !== undefined ? '&id=' + id : ''));
    });
});