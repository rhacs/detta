// Esperar a que la página termine de cargar
$(document).ready(function() {
    // Al presionar sobre el botón agregar
    $('[data-agregar]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/profesionales/agregar');
    });

    // Al presionar el botón editar o eliminar
    $('[data-accion]').on('click', function() {
        // Extraer acción
        var accion = $(this).data('accion');

        // Extraer id
        var id = $(this).data('id');

        // Verificar acción
        if (accion === 'editar') {
            $.LoadingOverlay('show');
            $(location).attr('href', '/detta/panel/profesionales/editar/' + id);
        } else if (accion === 'borrar') {
            if (confirm('¿Está seguro de querer eliminar el registro?')) {
                $.LoadingOverlay('show');
                $(location).attr('href', '/detta/panel/profesionales/eliminar/' + id);
            }
        }
    });

    // Al presionar el botón cancelar
    $('[data-cancelar]').on('click', function() {
        $.LoadingOverlay('show');
        history.back(1);
    });

    // Al presionar sobre una fila de la tabla
    $('[data-profid]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/profesionales/ver/' + $(this).data('profid'));
    });

    // Al presionar una fila de la tabla clientes
    $('[data-empresaid]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/clientes/ver/' + $(this).data('empresaid'));
    });

    // Al presionar una fila de la tabla capacitaciones
    $('[data-capacitacionid]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/capacitaciones/ver/' + $(this).data('capacitacionid'));
    });

    // Al presionar una fila de la tabla asesorías
    $('[data-asesoriaid]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/asesorias/ver/' + $(this).data('asesoriaid'));
    });

    // Al presionar una fila de la tabla visitas
    $('[data-visitaid]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/visitas/ver/' + $(this).data('visitaid'));
    });
});