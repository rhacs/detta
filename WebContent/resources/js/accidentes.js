// Esperar a que termine de cargar la página
$(function() {
    // Al presionar el botón agregar
    $('[data-agregar]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');

        // Redireccionar
        $(location).attr('href', '/detta/panel/accidentes/agregar');
    });

    // Al presionar una fila del listado
    $('[data-accidenteid]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');

        // Obtener el identificador
        var id = $(this).data('accidenteid');

        // Redireccionar
        $(location).attr('href', '/detta/panel/accidentes/ver/' + id);
    });

    // Al presionar un botón de acción
    $('[data-accion]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');

        // Obtener acción
        var accion = $(this).data('accion');

        // Obtener identificador
        var id = $(this).data('id');

        // Verificar acción
        if (accion === 'editar') {
            // Redireccionar
            $(location).attr('href', '/detta/panel/accidentes/editar/' + id);
        } else if (accion === 'eliminar') {
            // Confirmar
            if (confirm('¿Está seguro de querer eliminar el registro?')) {
                // Redireccionar
                $(location).attr('href', '/detta/panel/accidentes/eliminar/' + id);
            } else {
                // Cerrar diálogo de carga
                $.LoadingOverlay('hide', true);
            }
        } else if (accion === 'cancelar') {
            // Ir atrás
            history.back(1);
        }
    });
});