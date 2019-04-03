import request from '@/utils/request'

export default async function(param = {}) {
  const result = await request({// 查询业务
    url: '/space/tree',
    method: 'post',
    param: param
  })

  const recursion = function(list) {
    list.map((v) => {
      if (v.children && v.children.length === 0) {
        delete (v.children)
      }
      if (v.children && v.children.length > 0) {
        recursion(v.children)
      }
    })
  }
  recursion(result.data)
  return result || {}
}
