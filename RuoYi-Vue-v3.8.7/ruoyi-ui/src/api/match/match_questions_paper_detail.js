import request from '@/utils/request'

// 查询模拟试卷详情列表
export function listMatch_questions_paper_detail(query) {
  return request({
    url: '/match/match_questions_paper_detail/list',
    method: 'get',
    params: query
  })
}

// 查询模拟试卷详情详细
export function getMatch_questions_paper_detail(id) {
  return request({
    url: '/match/match_questions_paper_detail/' + id+'/'+id ,
    method: 'get'
  })
}

// 查询模拟试卷详情详细
export function getMatch_questions_paper_detail_by_paperid(paperid) {
  return request({
    url: '/match/match_questions_paper_detail/' + paperid,
    method: 'get'
  })
}


export function addMatch_questions_paper_detail(data) {
  return request({
    url: '/match/match_questions_paper_detail',
    method: 'post',
    data: data
  });
}
// 修改模拟试卷详情
export function updateMatch_questions_paper_detail(data) {
  return request({
    url: '/match/match_questions_paper_detail',
    method: 'put',
    data: data
  })
}

// 删除模拟试卷详情
export function delMatch_questions_paper_detail(id) {
  return request({
    url: '/match/match_questions_paper_detail/' + id,
    method: 'delete'
  })
}
