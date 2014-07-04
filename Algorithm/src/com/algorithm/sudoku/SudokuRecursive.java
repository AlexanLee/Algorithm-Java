/**
 * FileName:SudokuRecursive.java
 * Date:2014��3��6��
 * Author:Administrator
 * Version:1.0
 */
package com.algorithm.sudoku;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextField;

/**
 * @author Administrator
 *
 */
public class SudokuRecursive {
	ArrayList<JTextField> list = new ArrayList<JTextField>();   // �����ĸ���
	ArrayList<ArrayList<Integer>> insertArray = new ArrayList<ArrayList<Integer>>();
	/**
	 * <li> ����һ�����������������
	 * 		private void solution(int index, ArrayList<ArrayList<Integer>> insertArray)
	 * <p><p><P> ����: ͨ���ݹ�ķ�ʽ����һ�������������������ʵ��ԭ��������ͨ��������±��жϵ�ǰλ���Ƿ��п�������
	 * �����֣�����н���ǰ�������룬������ǰ�Ŀ���������ֵļ��ϣ���ȥ�Ѿ���������֣����浽��������һ������ArrayList<ArrayList<Integer>> insertArray
	 * �У��ٵݹ顣�������޸�ǰһ��������µ���������֣����ǰһ�������µĶ�Ӧ��ArrayList<ArrayList<Integer>> insertArray�е�ArrayList<Integer>�л��п�
	 * ������������޸�ǰһ�������µ����֣��ٵݹ飬����ǰһ��λ���е������ÿգ����ݹ����ָ����ǰһλ�ã�Ȼ��ݹ顣�����У������ǰλ���ϵ�ArrayList<ArrayList<Integer>> insertArray
	 * �а����˵�ǰλ�õĿɲ������ֵļ��ϣ����ʾ���εݹ�Ϊ�ϴβ��벻�ɹ����µģ����Խ������ֵļ��ϵ�����ǰ�ɲ�������ֵļ��ϣ�����һ������Լ����ظ��жϵĴ���
	 * ��ߴ���Ч�ʣ�ͬʱʹ�����߼�����
	 * @param index list���±�
	 * @param insertArray �ɲ������ֵļ��ϣ��Ѿ�ȥ���Ѳ����)�ļ���
	 */
	@SuppressWarnings("unused")
	private void solution(int index, ArrayList<ArrayList<Integer>> insertArray) {
		if (index>80) return;
		else {
			ArrayList<Integer> insert = insertable(index/9, index%9);
			if (insertArray.size()>index) insert = insertArray.remove(index);  // �β���ǳ��ؼ���
			if (insert.size()>0) {
				list.get(index).setText(insert.remove(0).toString());
				insertArray.add(insert);
				solution(++index, insertArray); // dg
			}
			else {
				--index;
				ArrayList<Integer> preInsert = insertArray.get(index);
				if (preInsert.size()>0) {
					list.get(index).setText(insertArray.get(index).remove(0).toString());
					solution(++index, insertArray); // dg
				}
				else {
					list.get(index).setText("");
					solution(index, insertArray); //dg
				}
			}
		}
	}
	
	/**
	 * description��������� from-to ������
	 * @param from ���ĸ����ֿ�ʼ
	 * @param to ���ĸ����ֽ���
	 * @return (int) �����
	 */
	@SuppressWarnings("unused")
	private int randomNum(int from, int to) {
		return  from + (int) (Math.random()*(to-from+1));
	}
	/**
	 * description: ��ȡ��ǰλ�ÿ����������
	 * @param indexI	������
	 * @param indexJ	������
	 * @return base ����������ֵļ��� 
	 */
	private ArrayList<Integer> insertable(int indexI, int indexJ) {
		
		ArrayList<Integer> base = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) base.add(i);
		Collections.shuffle(base);
		String str = null;
		// �ж�������
		int regionI = (indexI/3)*3;
		int regionJ = (indexJ/3)*3;
		for (int i = regionI; i < regionI+3; i++) {
			for (int j = regionJ; j < regionJ+3; j++) {
				str = list.get(i*9+j).getText();	
				// ֮��base��ֻʣ�¸����п��������
				if (str!=null&&!str.equals("")) { 
					if (base.contains(Integer.parseInt(str)))	
						base.remove(base.indexOf(Integer.parseInt(str)));	
				}
			}
		}
		// �ж�������
		for (int j = 0; j < 9; j++) {
			str = list.get(indexI*9+j).getText();
			// ֮��base��ֻʣ�����п����������
			if (str!=null&&!str.equals("")) { 
				if (base.contains(Integer.parseInt(str)))
					base.remove(base.indexOf(Integer.parseInt(str)));
			}
		}
		// �ж�������
		for (int i = 0; i < 9; i++) {
			str = list.get(i*9+indexJ).getText();
			 // ֮��base��ֻʣ�����п����������
			if (str!=null&&!str.equals("")) { 
				if (base.contains(Integer.parseInt(str)))
					base.remove(base.indexOf(Integer.parseInt(str)));
			}
		}
		base.trimToSize();
		return base;
	}
	
}
