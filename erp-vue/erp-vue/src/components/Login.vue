
<template>
  <div>
    <div class="outer_label">
      <img class="inner_label login_logo" src="../assets/logo.png">
    </div>
    <div class="login_form">
      <input type="text"  class="qxs-ic_user qxs-icon"  placeholder="用户名" v-model="formData.username">
      <input type="text"  class="qxs-ic_password qxs-icon"  placeholder="密码" v-model="formData.passwd">
      <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
      <el-button class="login_btn" @click.native="login" type="primary" round :loading="isBtnLoading">{{btnText}}</el-button>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      formData: {
        username: '',
        passwd: ''
      },
      isBtnLoading: false
    }
  },
  computed: {
    btnText () {
      if (this.isBtnLoading) return '登录中...'
      return '登录'
    }
  },
  methods: {
    login () {
      if (!this.formData.username) {
        this.$message.error('请输入用户名')
        return
      }
      if (!this.formData.passwd) {
        this.$message.error('请输入密码')
        return
      }
      this.isBtnLoading = true
      this.$axios.post('login_p', JSON.stringify(this.formData))
    }
  }
}
</script>
<style>
.login_form {
  padding-top: 10%;
  padding-left: 10%;
  padding-right: 10%;
}
.qxs-ic_user {
  background-size: 13px 15px;
  background-position: 3%;
}
.qxs-ic_password {
  background-size: 13px 15px;
  background-position: 3%;
  margin-bottom: 20px;
}
.login_logo {
  height: 100%;
}
.login_btn {
  width: 100%;
  font-size: 16px;
  background: -webkit-linear-gradient(left, #000099, #2154FA); /* Safari 5.1 - 6.0 */
  background: -o-linear-gradient(right, #000099, #2154FA); /* Opera 11.1 - 12.0 */
  background: -moz-linear-gradient(right, #000099, #2154FA); /* Firefox 3.6 - 15 */
  background: linear-gradient(to right, #000099 , #2154FA); /* 标准的语法 */
  filter: brightness(1.4);
}
</style>
