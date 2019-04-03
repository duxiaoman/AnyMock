import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({
    url: '/host_info/core',
    method: 'post',
    param: param
  })
  return result || {}
}
