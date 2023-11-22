import './index.css'

export default function UpLoadPageView() {
    return(
        <>
            <div className="upload-containor">
                <div className='upload-message'>
                    영상을 업로드 후 카테고리 4개를 설정해 주세요
                </div>
                <div className='upload-box'>
                    <div className='upload-box2'>
                        영상을 넣어주세요
                        <button className='upload-file-button'>파일첨부</button>
                    </div>
                </div>
                <div className='upload-category'>
                    <div className='menu'>
                        <div className='menu-name'>
                            카테고리 선택
                        </div>
                    </div>
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
                        <select name='장르'>
                            <option value={'스릴러'}>스릴러</option>
                            <option value={'코믹'}>코믹</option>
                        </select>
                        <select name='직군'>
                            <option value={'회사원'}>회사원</option>
                            <option value={'식당직원'}>식당직원</option>
                            <option value={'범인'}>범인</option>
                        </select>
                </div>
                <div className='upload-button'>
                    <button>업로드</button>
                </div>
            </div>
        </>
    )
}