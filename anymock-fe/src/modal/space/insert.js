import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({
    url: '/space/insert',
    method: 'post',
    param: param
  })
  return result || {}
}
