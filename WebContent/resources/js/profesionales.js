// Esperar a que la página termine de cargar
$(document).ready(function() {
    // Al presionar sobre el botón agregar
    $('[data-agregar]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/profesionales/agregar');
    });

    // Al presionar sobre una fila de la tabla
    $('[data-profid]').on('click', function() {
        $.LoadingOverlay('show');
        $(location).attr('href', '/detta/panel/profesionales/ver/' + $(this).data('profid'));
    });
});