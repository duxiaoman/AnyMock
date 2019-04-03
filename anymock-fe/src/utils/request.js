import axios from 'axios'
import { Message } from 'element-ui'
axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 10000, // request timeout
  withCredentials: true,
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json;charset=UTF-8;'
  }
})
axios.interceptors.request.use(
  config => {
    return config
  },
  error => {
    // Do something with request error
    Promise.reject(error)
  }
)

// response interceptor
axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default function(data = {}) {
  if (data.method.toLowerCase() === 'post') {
    return axios({
      method: 'post',
      baseURL: process.env.BASE_API,
      url: data.url,
      data: data.param
    }).then((response) => {
      return response.data
    }).catch((e) => {
      console.log(data.url + '=>接口请求错误，错误内容为:' + e)
    })
  }
  if (data.method.toLowerCase() === 'get') {
    return axios({
      baseURL: process.env.BASE_API,
      method: 'get',
      url: data.url,
      params: data.param
    }).then((response) => {
      return response.data
    }).catch((e) => {
      console.log(data.url + '接口请求错误，错误内容为:' + e)
    })
  }
}
