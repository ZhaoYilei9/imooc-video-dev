package com.imooc.mapper;

import com.imooc.pojo.Videos;
import com.imooc.vo.VideosVo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VideosMapper extends Mapper<Videos> {
    @Select("SELECT v.* FROM videos v where v.id in " +
            "(select ulv.video_id from users_like_videos ulv " +
            "where ulv.user_id = #{userId})")
    List<Videos> showMyLike(@Param("userId") String userId);

    /**
     *
     * @param videoDesc 一个很恶心的问题模糊查询
     *                  '%${videoDesc}%'不能写成'%#{videoDesc}%'
     * @return
     */
    @Select("<script>select v.*,u.face_image as faceImage,u.nickname as nickname " +
            "from videos v left join users u on v.user_id = u.id " +
            "where 1 = 1 " +
            "<if test=\" videoDesc != null and videoDesc != '' \">" +
            "and v.video_desc like '%${videoDesc}%' " +
            "</if>" +
            "order by v.create_time desc" +
            "</script>")
    List<VideosVo> showAll(@Param("videoDesc") String videoDesc);
}