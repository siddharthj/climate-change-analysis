// https://insights.stackoverflow.com/survey/2018/#technology-most-loved-dreaded-and-wanted-languages

    $("#button-query1").click(function(e) {
        //e.preventDefault();
        console.log("Inside Button query");
        $.ajax({
            type: "GET",
            url: "/d3testdata",
            dataType:'json',
            data: {
                name: "testing my beautiful code :-*"
            },
            success: function(result) {
                //alert('testing my beautiful code :-*');
                printChart(result)
            },
            error: function(result) {
                //alert('error');
            }
        });
    });

    function printChart(data) {
        var a = document.getElementById('layout1')
        a.innerHTML= '<div id="container"><svg/></div>'

        const svg = d3.select('svg');
        const svgContainer = d3.select('#container');

        const margin = 200;
        const width = 1000 - 2 * margin;
        const height = 800 - 2 * margin;

        const chart = svg.append('g')
          .attr('transform', `translate(${margin}, ${margin})`);

        const xScale = d3.scaleBand()
          .range([0, width])
          .domain(data.map((s) => s.countryCode))
          .padding(0.3)

        const yScale = d3.scaleLinear()
          .range([height, 0])
          .domain([0, 100]);

        // vertical grid lines
        // const makeXLines = () => d3.axisBottom()
        //   .scale(xScale)

        const makeYLines = () => d3.axisLeft()
          .scale(yScale)

        chart.append('g')
          .attr('transform', `translate(0, ${height})`)
          .call(d3.axisBottom(xScale));

        chart.append('g')
          .call(d3.axisLeft(yScale));

        // vertical grid lines
        // chart.append('g')
        //   .attr('class', 'grid')
        //   .attr('transform', `translate(0, ${height})`)
        //   .call(makeXLines()
        //     .tickSize(-height, 0, 0)
        //     .tickFormat('')
        //   )

        chart.append('g')
          .attr('class', 'grid')
          .call(makeYLines()
            .tickSize(-width, 0, 0)
            .tickFormat('')
          )

        const barGroups = chart.selectAll()
          .data(data)
          .enter()
          .append('g')

        barGroups
          .append('rect')
          .attr('class', 'bar')
          .attr('x', (g) => xScale(g.countryCode))
          .attr('y', (g) => yScale(g.islandCount))
          .attr('height', (g) => height - yScale(g.islandCount))
          .attr('width', xScale.bandwidth())
          .on('mouseenter', function (actual, i) {
            d3.selectAll('.islandCount')
              .attr('opacity', 0)

            d3.select(this)
              .transition()
              .duration(300)
              .attr('opacity', 0.6)
              .attr('x', (a) => xScale(a.countryCode) - 5)
              .attr('width', xScale.bandwidth() + 10)

            const y = yScale(actual.islandCount)

            line = chart.append('line')
              .attr('id', 'limit')
              .attr('x1', 0)
              .attr('y1', y)
              .attr('x2', width)
              .attr('y2', y)

            barGroups.append('text')
              .attr('class', 'divergence')
              .attr('x', (a) => xScale(a.countryCode) + xScale.bandwidth() / 2)
              .attr('y', (a) => yScale(a.islandCount) + 30)
              .attr('fill', 'white')
              .attr('text-anchor', 'middle')
              .text((a, idx) => {
                const divergence = (a.islandCount - actual.islandCount).toFixed(1)

                let text = ''
                if (divergence > 0) text += '+'
                text += `${divergence}%`

                return idx !== i ? text : '';
              })

          })
          .on('mouseleave', function () {
            d3.selectAll('.islandCount')
              .attr('opacity', 1)

            d3.select(this)
              .transition()
              .duration(300)
              .attr('opacity', 1)
              .attr('x', (a) => xScale(a.countryCode))
              .attr('width', xScale.bandwidth())

            chart.selectAll('#limit').remove()
            chart.selectAll('.divergence').remove()
          })

        barGroups
          .append('text')
          .attr('class', 'islandCount')
          .attr('x', (a) => xScale(a.countryCode) + xScale.bandwidth() / 2)
          .attr('y', (a) => yScale(a.islandCount) + 30)
          .attr('text-anchor', 'middle')
          .text((a) => `${a.islandCount}%`)

        svg
          .append('text')
          .attr('class', 'label')
          .attr('x', -(height / 2) - margin)
          .attr('y', margin / 2.4)
          .attr('transform', 'rotate(-90)')
          .attr('text-anchor', 'middle')
          .text('Love meter (%)')

        svg.append('text')
          .attr('class', 'label')
          .attr('x', width / 2 + margin)
          .attr('y', height + margin * 1.7)
          .attr('text-anchor', 'middle')
          .text('Languages')

        svg.append('text')
          .attr('class', 'title')
          .attr('x', width / 2 + margin)
          .attr('y', 40)
          .attr('text-anchor', 'middle')
          .text('Island Count')

        svg.append('text')
          .attr('class', 'source')
          .attr('x', width - margin / 2)
          .attr('y', height + margin * 1.7)
          .attr('text-anchor', 'start')
          .text('Source: DBMS Project, 2019')
    }

