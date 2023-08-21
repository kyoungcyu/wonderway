package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AttachDeVO;
import kr.or.ddit.vo.BusiVO;
import kr.or.ddit.vo.CvfcVO;
import kr.or.ddit.vo.MemVO;
import kr.or.ddit.vo.MsgVO;
import kr.or.ddit.vo.RmVO;
import kr.or.ddit.vo.RvwVO;

public interface MmAcmService {

	//숙소 검색-체크인날짜,인원,방개수로
	public List<BusiVO> searchAcm(Map<String, String> map);
	
	
	//숙소검색-숙소명으로
	public List<BusiVO> searchForName(Map<String, String> map);
	
	
	//숙소 총개수-체크인날짜,인원,방개수로
	public List<BusiVO> getTotalAcm(Map<String, String> map);
	
	
	//숙소 총개수-숙소명으로
	public List<BusiVO> getTotalAcmNm(Map<String, String> map);
	
	
	//상단광고 노출
	public List<BusiVO> getTopAd(String busiLoc);
	
	
	//방 최대값 가져오기
	public int getMaxRmPr(Map<String, String> map);
	
	
	//검색필터
	public List<BusiVO> searchFilter(Map<String, Object> map);


	//필터 결과 총개수
	public List<BusiVO> getTotalFilter(Map<String, Object> map);
	
	
	//숙소 상세 페이지
	public BusiVO detailAcm(BusiVO busiVO);
	
	
	//이미지 가져오기
	public List<AttachDeVO> getImg(String busiId);
	
	
	//편의시설 가져오기
	public List<CvfcVO> getCvfc(String busiId);
	
	
	//아동,펫,장애인,흡연 여부 
	public Map<String, String> availKPDS(String busiId);

	
	//예약 가능 객실 가져오기
	public List<RmVO> getRm(Map<String, String> map);
	
	
	//예약된 객실 가져오기
	public List<RmVO> getReservedRm(Map<String, String> map);
	
	
	//방 상세정보 
	public List<RmVO> rmDetail(String rmId);


	//리뷰 가져오기
	public List<RvwVO> getRvw(Map<String, String> map);

	
	//리뷰 별점 비율 
	public Map<String, Double> getRvwRatio(String busiId);
	
		
	//리뷰 총개수
	public int getTotalRvw(String busiId);

	
	//예약정보 넘겨주기 
	public BusiVO rsvInfo(Map<String, String> map);
	
	
	//로그인한 회원정보 가져오기
	public MemVO getMemInfo(String memId);
	
	
	//비회원 예약시 테이블에 인서트
	public int insertGuestRsv(Map<String, String> data);
	
	
	//회원 예약시 테이블에 인서트
	public int insertMemRsv(Map<String, String> map);

	
	//쿠폰상태 변경  
	public int changeCupnInfo(Map<String, String> map);
		
	
	//마일리지 상태 변경 
	public int changeMlg(Map<String, String> map);

	
	//쪽지 보내기 위한 업체id가져오기
	public String getId(String busiNm);

	
	//쪽지 보내기
	public int msgInsert(MsgVO msgVO);


	
	
}