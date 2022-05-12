<template>
  <div class="app-container">
    <el-input
      v-model="filterText"
      placeholder="Filter keyword"
      style="margin-bottom: 30px"
    />

    <el-tree
      ref="tree2"
      :data="data"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />
  </div>
</template>

<script>
import subject from "@/api/edu/subject"
export default {

  data() {
    return {
      filterText: '',
      data: [],
      defaultProps: {
        children: 'childrens',
        label: 'title'
      }
    }
  },
  created() {
    this.getSubjectList()
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.title.indexOf(value) !== -1
    },
    getSubjectList() {
      subject.getSubjectList().then(response => {
        this.data = response.data.items
      })
    }
  }
}
</script>

