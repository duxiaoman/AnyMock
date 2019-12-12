import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({
    url: '/space/update',
    method: 'post',
    param: param
  })
  return result || {}
}
