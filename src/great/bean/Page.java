package great.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Page implements Serializable{

	private List records;// ��¼ 
	private int currentPageNum;// ��ǰҳ��
	private int totalPageNum;// ��ҳ��
	private int totalRecordsNum;// �ܼ�¼��
	private int pageSize;// ÿҳҪ��ʾ�ļ�¼��

	// ���ڲ�ѯ���ݿ���
	private int startIndex;// ÿҳ��¼��ʼ������ֵ
	private int endIndex;// ÿҳ��¼��ʼ������ֵ

	// ҳ����ʾҳ��
	private int prevPageNum;// ��һҳҳ��
	private int nextPageNum;// ��һҳҳ��

	public Page(int currentPageNum, int totalRecordsNum, int pageSize) {
		super();
		this.currentPageNum = currentPageNum;
		this.totalPageNum = totalPageNum;
		this.pageSize = pageSize;
		// ������ҳ��
		totalPageNum = (totalRecordsNum % pageSize == 0 ? totalRecordsNum / pageSize :( totalRecordsNum / pageSize)+1) ;
		prevPageNum = currentPageNum - 1 > 0 ? currentPageNum - 1 : 1;// ��ʼ����һҳ��ֵ
		nextPageNum = currentPageNum + 1 > totalPageNum ? currentPageNum : currentPageNum + 1;// ��ʼ����һҳ��ֵ

		// ����ÿҳ��ʼ������ֵ
		startIndex = (currentPageNum - 1) * pageSize;
		endIndex = currentPageNum * pageSize + 1;
	}

	public Page() {
		super();
	}

	public Page(List records, int currentPageNum, int totalPageNum, int totalRecordsNum, int pageSize, int startIndex,
			int endIndex, int prevPageNum, int nextPageNum) {
		super();
		this.records = records;
		this.currentPageNum = currentPageNum;
		this.totalPageNum = totalPageNum;
		this.totalRecordsNum = totalRecordsNum;
		this.pageSize = pageSize;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.prevPageNum = prevPageNum;
		this.nextPageNum = nextPageNum;
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}

	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getPrevPageNum() {
		return prevPageNum;
	}

	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	@Override
	public String toString() {
		return "Page [records=" + records + ", currentPageNum=" + currentPageNum + ", totalPageNum=" + totalPageNum
				+ ", totalRecordsNum=" + totalRecordsNum + ", pageSize=" + pageSize + ", startIndex=" + startIndex
				+ ", endIndex=" + endIndex + ", prevPageNum=" + prevPageNum + ", nextPageNum=" + nextPageNum + "]";
	}

}
