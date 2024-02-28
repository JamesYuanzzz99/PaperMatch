import request from '@/utils/request'

// 查询模拟试题列表
export function listMatch_questions_info(query) {
  return request({
    url: '/match/match_questions_info/list',
    method: 'get',
    params: query
  })
}



// 查询模拟试题详细
export function getMatch_questions_info(id) {
  return request({
    url: '/match/match_questions_info/' + id,
    method: 'get'
  })
}


// 新增模拟试题
export function addMatch_questions_info(data) {
  return request({
    url: '/match/match_questions_info',
    method: 'post',
    data: data
  })
}

// 修改模拟试题
export function updateMatch_questions_info(data) {
  return request({
    url: '/match/match_questions_info',
    method: 'put',
    data: data
  })
}

// 删除模拟试题
export function delMatch_questions_info(id) {
  return request({
    url: '/match/match_questions_info/' + id,
    method: 'delete'
  })
}



// 删除模拟试题
export function delMatch_questions_info_answerfile(params) {
  return request({
    url: '/match/match_questions_info/deleteFile',
    method: 'get',
    params: params
  })
}
