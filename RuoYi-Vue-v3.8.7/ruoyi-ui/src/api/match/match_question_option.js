import request from '@/utils/request'

// 查询试题选项列表
export function listMatch_question_option(query) {
  return request({
    url: '/match/match_question_option/list',
    method: 'get',
    params: query
  })
}

// 查询试题选项详细
export function getMatch_question_option(id) {
  return request({
    url: '/match/match_question_option/' + id,
    method: 'get'
  })
}

// 新增试题选项
export function addMatch_question_option(data) {
  return request({
    url: '/match/match_question_option',
    method: 'post',
    data: data
  })
}


export function addMatchQuestionOption(optionsList) {
  return axios.post('/match/match_question_option', { optionsList });
}

export function addMatchQuestionInfoAnswerFile(optionsList) {
  return axios.post('/match/match_questions_info/answer', { optionsList });
}

// 修改试题选项
export function updateMatch_question_option(data) {
  return request({
    url: '/match/match_question_option',
    method: 'put',
    data: data
  })
}

// 删除试题选项
export function delMatch_question_option(id) {
  return request({
    url: '/match/match_question_option/' + id,
    method: 'delete'
  })
}
