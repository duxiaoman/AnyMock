import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({
    url: '/interface_tcp/conflictDetection',
    method: 'post',
    param: param
  })
  return result || {}
}
