document.addEventListener('DOMContentLoaded', function () {
    const ctx = document.getElementById('billetChart')?.getContext('2d');
  
    if (ctx && typeof billetLabels !== 'undefined' && typeof billetCounts !== 'undefined') {
      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: billetLabels,
          datasets: [{
            label: 'Billets Vendus',
            data: billetCounts,
            backgroundColor: 'rgba(54, 162, 235, 0.7)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
            borderRadius: 5
          }]
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
              ticks: {
                stepSize: 1
              }
            }
          }
        }
      });
    }
  });
  