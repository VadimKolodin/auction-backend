package ru.stud.auc.common;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.common.model.SubtagDto;
import ru.stud.auc.common.model.TagDto;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommonsController implements CommonsApi {

    @Override
    public List<TagDto> getTags() {
        return Arrays.stream(Tag.values()).map( tag -> new TagDto(tag.name(), tag.getDescription())).toList();
    }

    @Override
    public List<SubtagDto> getSubtags() {
        return Arrays.stream(SubTag.values()).map(tag -> new SubtagDto(tag.name(), tag.getDescription())).toList();
    }
}
