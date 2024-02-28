import request from '@/utils/request'

// 查询试题类型列表
export function listMatch_question_type(query) {
  return request({
    url: '/match/match_question_type/list',
    method: 'get',
    params: query
  })
}

// 查询试题类型详细
export function getMatch_question_type(id) {
  return request({
    url: '/match/match_question_type/' + id,
    method: 'get'
  })
}

// 新增试题类型
export function addMatch_question_type(data) {
  return request({
    url: '/match/match_question_type',
    method: 'post',
    data: data
  })
}

// 修改试题类型
export function updateMatch_question_type(data) {
  return request({
    url: '/match/match_question_type',
    method: 'put',
    data: data
  })
}

// 删除试题类型
export function delMatch_question_type(id) {
  return request({
    url: '/match/match_question_type/' + id,
    method: 'delete'
  })
}
