// Esperar a que termine de cargar la página
$(function() {
    // Al presionar sobre el botón agregar
    $('[data-agregar]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');

        // Redireccionar
        $(location).attr('href', '/detta/panel/clientes/agregar');
    });

    // Al presionar uno de los botones de acción
    $('[data-accion]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');

        // Obtener accion
        var accion = $(this).data('accion');

        // Obtener identificador
        var id = $(this).data('id');

        // Filtrar acción
        if (accion === 'editar') {
            // Redireccionar
            $(location).attr('href', '/detta/panel/clientes/editar/' + id);
        } else if (accion === 'eliminar') {
            // Confirmar eliminación
            if (confirm('¿Está seguro de querer eliminar el registro?')) {
                // Redireccionar
                $(location).attr('href', '/detta/panel/clientes/eliminar/' + id);
            }
        } else if (accion === 'cancelar') {
            history.back(1);
        }
    });

    // Al presionar sobre una fila de la tabla
    $('[data-clienteid]').on('click', function() {
        // Mostrar dialogo de carga
        $.LoadingOverlay('show');

        // Recuperar identificador
        var id = $(this).data('clienteid');

        // Redireccionar
        $(location).attr('href', '/detta/panel/clientes/ver/' + id);
    });

    // Al presionar sobre una de las filas de la tabla accidentes
    $('[data-accidenteid]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');

        // Obtener id
        var id = $(this).data('accidenteid');

        // Redireccionar
        $(location).attr('href', '/detta/panel/accidentes/ver/' + id);
    });
});