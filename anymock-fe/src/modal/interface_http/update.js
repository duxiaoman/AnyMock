import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({
    url: '/interface_http/update',
    method: 'post',
    param: param
  })
  return result || {}
}
