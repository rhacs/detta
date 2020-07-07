// Esperar a que termine de cargar la página
$(function() {
    // Al presionar el botón agregar
    $('[data-agregar]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');
        
        // Redireccionar
        $(location).attr('href', '/detta/panel/capacitaciones/agregar');
    });
    
    // Al presionar sobre una fila de la tabla
    $('[data-capid]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');
        
        // Obtener identificador
        var id = $(this).data('capid');
        
        // Redireccionar
        $(location).attr('href', '/detta/panel/capacitaciones/ver/' + id);
    });

    // Al presionar uno de los botones de acción
    $('[data-accion]').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');

        // Recuperar acción
        var accion = $(this).data('accion');
        
        // Recuperar identificador
        var id = $(this).data('id');
        
        // Verificar acción
        if(accion === 'editar') {
            // Redireccionar
            $(location).attr('href', '/detta/panel/capacitaciones/editar/' + id);
        } else if(accion === 'eliminar') {
            // Confirmar
            if(confirm('¿Está seguro de querer eliminar el registro?')) {
                // Redireccionar
                $(location).attr('href', '/detta/panel/capacitaciones/eliminar/' + id);
            } else {
                // Cerrar diálogo de carga
                $.LoadingOverlay('hide', true);
            }
        } else if (accion === 'cancelar') {
            // Volver atrás
            history.back(1);
        }
    })
});