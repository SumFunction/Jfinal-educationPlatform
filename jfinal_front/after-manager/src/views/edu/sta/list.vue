<template>
  <div class="app-container">
    <h1 style="margin: 0px auto; text-align: center">热门课程销售额</h1>
    <div class="chart-container">
      <div id="chart" class="chart" style="height: 500px; width: 100%" />
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import sta from '@/api/edu/static'
export default {
  data() {
    return {
      chart: null,
      data: [],
    }
  },
  methods: {
    setChart() {
      var chartDom = document.getElementById('chart');
      var myChart = echarts.init(chartDom);
      var option;
      option = {
        legend: {
          top: 'bottom'
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: 'Nightingale Chart',
            type: 'pie',
            radius: [50, 200],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: this.data
          }
        ]
      };
      option && myChart.setOption(option);
    }
  },
  mounted() {
    sta.getCourseSell().then(response => {
      var total = 0
      for (let i in response.data) {
        if (total > 5)
          break
        this.data.push({
          "value": response.data[i],
          "name": i
        })
        total += 1
      }
      this.setChart()
    })

  }
}
</script>

<style>
</style>