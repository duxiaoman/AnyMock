import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({// 查询业务
    url: '/interface_tcp/selectById',
    method: 'post',
    param: param
  })
  return result || {}
}
