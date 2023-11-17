import './index.css';

export default function MainPageView() {
    return (
        <>
            <div>Hello Would</div>
            <div className='category'>
                <div className='menu'>성별 선택</div>
                <select name='성별'>
                    <option value={'남자'}>남성</option>
                    <option value={'여자'}>여성</option>
                </select>
                <select name='나이대'>
                    <option value={'유아'}>저 연령</option>
                    <option value={'10대'}>10대</option>
                    <option value={'20대'}>20대</option>
                    <option value={'30대'}>30대</option>
                    <option value={'40대'}>40대</option>
                    <option value={'50대'}>고 연령</option>
                </select>
                <select name='연령'>
                    <option value={'남자'}>남성</option>
                    <option value={'여자'}>여성</option>
                </select>
                <select name='직군'>
                    <option value={'회사원'}>회사원</option>
                    <option value={'식당직원'}>식당직원</option>
                </select>
            </div>
        </>
    )
}