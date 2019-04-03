import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({// 查询业务
    url: '/interface_http/selectById',
    method: 'post',
    param: param
  })
  return result || {}
}
