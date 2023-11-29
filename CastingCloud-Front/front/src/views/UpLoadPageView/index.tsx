import { useNavigate } from 'react-router-dom'
import './index.css'
import { ChangeEvent, useEffect, useRef, useState } from 'react';
import { useCookies } from 'react-cookie';
import axios, { AxiosResponse } from 'axios';
import ResponseDto from 'src/apis/response';
import { PostVideoResponseDto } from 'src/apis/response/upload';
import { FILE_UPLOAD_URL, POST_VIDEO_URL, authorizationHeader, multipartHeader } from 'src/constants/api';
import { PostVideoDto } from 'src/apis/request/upload';

export default function UpLoadPageView() {

    const navigator = useNavigate();

    const videoRef = useRef<HTMLInputElement | null>(null);
    const [cookies] = useCookies();
    const [videoUrl, setVideoUrl] = useState<string>('');
    const [videoCategoryGender, setVideoCagetoryGender] = useState<string>('');
    const [videoCategoryAge, setVideoCategoryAge] = useState<string>('');
    const [videoCategoryPosition, setVideoCategoryPosition] = useState<string>('');
    const [videoCategoryGenre, setVideoCategoryGenre] = useState<string>('');

    const accessToken = cookies.accessToken;

    const postVideoResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<PostVideoResponseDto>
        if (!result || !data) {
            alert(message)
            return;
        }
        navigator('/main');
    }

    const videoUploadResponsehandler = (response: AxiosResponse<any, any>) => {
        const fileUrl = response.data as string;
        if(!fileUrl) return;
        setVideoUrl(fileUrl);
    }

    const onVideoUploadChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const data = new FormData();
        data.append('file', event.target.files[0]);

        axios.post(FILE_UPLOAD_URL, data, multipartHeader())
            .then((response) => videoUploadResponsehandler(response))
            .catch((error) => onVideoUploadErrorHandler(error))
    }

    const onWirteHandler = () => {
        if(!videoUrl.trim() || !videoCategoryGender.trim || !videoCategoryAge.trim() || !videoCategoryGenre.trim() || !videoCategoryPosition.trim()) {
            alert('모든 내용을 선택해주세요')
            return;
        }
        postVideo();
    }

    const postVideo = () => {
        const data : PostVideoDto = {videoUrl, videoCategoryAge, videoCategoryGender, videoCategoryGenre, videoCategoryPosition};
        axios.post(POST_VIDEO_URL, data, authorizationHeader(accessToken))
            .then((response) => postVideoResponseHandler(response))
            .catch((error) => postVideoErrorHandler(error));
    }

    const postVideoErrorHandler = (error: any) => {
        console.log(error.message);
    }

    const onVideoUploadErrorHandler = (error: any) => {
        console.log(error.message);
    }

    useEffect(() => {
        if(!accessToken){
            alert('로그인이 필요한 작업입니다.');
            navigator('/');
        }
    },[]);

    return(
        <>
            <div className="upload-containor">
                <div className='upload-message'>
                    영상을 업로드 후 카테고리 4개를 설정해 주세요
                </div>
                <div className='upload-box'>
                    <div className='upload-box2'>
                        영상을 넣어주세요
                        <button className='upload-file-button'>
                            파일첨부
                            <input ref={videoRef} type='file' accept='video/*'></input>
                        </button>
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