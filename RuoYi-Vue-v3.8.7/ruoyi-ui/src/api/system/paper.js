import request from '@/utils/request'

// 查询模拟试卷主列表
export function listPaper(query) {
  return request({
    url: '/system/paper/list',
    method: 'get',
    params: query
  })
}

// 查询模拟试卷主详细
export function getPaper(id) {
  return request({
    url: '/system/paper/' + id,
    method: 'get'
  })
}

// 新增模拟试卷主
export function addPaper(data) {
  return request({
    url: '/system/paper',
    method: 'post',
    data: data
  })
}

// 修改模拟试卷主
export function updatePaper(data) {
  return request({
    url: '/system/paper',
    method: 'put',
    data: data
  })
}

// 删除模拟试卷主
export function delPaper(id) {
  return request({
    url: '/system/paper/' + id,
    method: 'delete'
  })
}
