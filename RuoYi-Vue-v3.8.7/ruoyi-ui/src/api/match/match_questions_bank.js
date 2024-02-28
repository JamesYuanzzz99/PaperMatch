import request from '@/utils/request'

// 查询模拟题库列表
export function listMatch_questions_bank(query) {
  return request({
    url: '/match/match_questions_bank/list',
    method: 'get',
    params: query
  })
}
//
export function listMatch_questions_bank2(query) {
  return request({
    url: '/match/match_questions_bank/list2',
    method: 'get',
    params: query
  })
}

// 查询模拟题库详细
export function getMatch_questions_bank(id) {
  return request({
    url: '/match/match_questions_bank/' + id,
    method: 'get'
  })
}

// 新增模拟题库
export function addMatch_questions_bank(data) {
  return request({
    url: '/match/match_questions_bank',
    method: 'post',
    data: data
  })
}

// 修改模拟题库
export function updateMatch_questions_bank(data) {
  return request({
    url: '/match/match_questions_bank',
    method: 'put',
    data: data
  })
}

// 删除模拟题库
export function delMatch_questions_bank(id) {
  return request({
    url: '/match/match_questions_bank/' + id,
    method: 'delete'
  })
}
