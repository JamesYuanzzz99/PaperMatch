import request from '@/utils/request'

// 查询编辑试卷列表
export function listMatch_question_paper(query) {
  return request({
    url: '/match/match_question_paper/list',
    method: 'get',
    params: query
  })
}

// 查询编辑试卷详细
export function getMatch_question_paper(id) {
  return request({
    url: '/match/match_question_paper/' + id,
    method: 'get'
  })
}


// 新增编辑试卷
export function addMatch_question_paper(data) {
  return request({
    url: '/match/match_question_paper',
    method: 'post',
    data: data
  })
}

// 修改编辑试卷
export function updateMatch_question_paper(data) {
  return request({
    url: '/match/match_question_paper',
    method: 'put',
    data: data
  })
}

// 删除编辑试卷
export function delMatch_question_paper(id) {
  return request({
    url: '/match/match_question_paper/' + id,
    method: 'delete'
  })
}
