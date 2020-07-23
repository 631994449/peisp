/*
 *  Document   : base_pages_dashboard.js
 *  Author     : pixelcave
 *  Description: Custom JS code used in Dashboard Page
 */

var BasePagesDashboard = function() {
    // Chart.js Chart, for more examples you can check out http://www.chartjs.org/docs
    var initDashChartJS = function(){
        // Get Chart Container
        var $dashChartLinesCon  = jQuery('.js-dash-chartjs-lines')[0].getContext('2d');

        // Set Chart and Chart Data variables
        var $dashChartLines, $dashChartLinesData;

        // Lines Chart Data
        var $dashChartLinesData = {
            labels: ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN'],
            datasets: [
                {
                    label: 'This Week',
                    fillColor: 'rgba(68,180,166, .07)',
                    strokeColor: 'rgba(68,180,166, .25)',
                    pointColor: 'rgba(68,180,166, .25)',
                    pointStrokeColor: '#fff',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(68,180,166, 1)',
                    data: [34, 42, 40, 65, 48, 56, 80]
                },
                {
                    label: 'Last Week',
                    fillColor: 'rgba(68,180,166, .1)',
                    strokeColor: 'rgba(68,180,166, .55)',
                    pointColor: 'rgba(68,180,166, .55)',
                    pointStrokeColor: '#fff',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(68,180,166, 1)',
                    data: [18, 19, 20, 35, 23, 28, 50]
                }
            ]
        };

        // Init Lines Chart
        $dashChartLines = new Chart($dashChartLinesCon).Line($dashChartLinesData, {
            scaleFontFamily: "'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif",
            scaleFontColor: '#999',
            scaleFontStyle: '600',
            tooltipTitleFontFamily: "'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif",
            tooltipCornerRadius: 3,
            maintainAspectRatio: false,
            responsive: true
        });
    };

    return {
        init: function () {
            // Init ChartJS chart
            initDashChartJS();
        }
    };
}();

// Initialize when page loads
jQuery(function(){ BasePagesDashboard.init(); });