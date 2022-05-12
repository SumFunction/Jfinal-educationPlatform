<template>
  <div>
    <div ref="alipayWap" v-html="payForm" />
  </div>
</template>

<script>
import pay from '@/api/pay'
import order from '@/api/order'
export default {
  data() {
    return {
      payForm: ""
    }

  },
  created() {
    pay.createPayForm(this.$route.params.oid).then(response => {
      this.payForm = response.data.payForm
      this.$nextTick(() => {
        // 提交支付表单
        this.$refs.alipayWap.children[0].setAttribute('target', '_blank');
        this.$refs.alipayWap.children[0].submit()
        setTimeout(() => {
          // this.toPayFlag = false;
        }, 500);
        this.$confirm('支付宝成功后请点击确定', '提示', {
          confirmButtonText: '已支付',
          nofirmButtonText: '取消'
        }).then(() => {
          //查询订单是否已经成功付款
          order.findOrderInfoByOrderNo(this.$route.params.oid).then(response => {
            if (response.data.item.status === 1)//表明已经付款成功
            {
              this.$message({
                type: "success",
                message: "成功付款！"
              })
            } else {
              this.$message({
                type: "error",
                message: "付款失败！"
              })
            }
            this.$router.push({ path: '/course/' + response.data.item.courseId })
          })
        })
      })
    })
  }
}
</script>

<style>
</style>