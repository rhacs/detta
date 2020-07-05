// Esperar a que se cargue la página
$(function() {
    // Al presionar un enlace
    $('a').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');
    });

    // Gráfico de accidentes por fecha
    new Chart($('#porFecha').get(0).getContext('2d'), {
        type: 'line',
        data: {
            labels: fechas,
            datasets: [{
                backgroundColor: 'rgba(23, 162, 184, 0.25)',
                borderColor: 'rgba(23, 162, 184, 0.75)',
                data: accidentes,
                label: '# de Accidentes'
            }]
        },
        options: {
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    // Gráfico de accidentes por clasificación (leve, grave, fatal, otro)
    new Chart($('#porClasificacion').get(0).getContext('2d'), {
        type: 'bar',
        data: {
            labels: clasificaciones,
            datasets: [{
                backgroundColor: 'rgba(23, 162, 184, 0.75)',
                data: clasificacionesAccidentes,
                label: '# de Accidentes'
            }]
        },
        options: {
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    // Gráfico de accidentes por tipo (trabajo, trayecto)
    new Chart($('#porTipo').get(0).getContext('2d'), {
        type: 'pie',
        data: {
            labels: porTipoEtiquetas,
            datasets: [{
                backgroundColor: [
                    'rgba(23, 162, 184, 0.75)',
                    'rgba(23, 162, 184, 0.35)'
                ],
                data: porTipoAccidentes
            }]
        },
        options: {
            maintainAspectRatio: false
        }
    });
});