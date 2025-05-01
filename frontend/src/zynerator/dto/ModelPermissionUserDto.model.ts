import { ActionPermissionDto } from "./ActionPermissionDto.model";
import { BaseDto } from "./BaseDto.model";
import { ModelPermissionDto } from "./ModelPermissionDto.model";
import { RoleDto } from "./RoleDto.model";
import { UserDto } from "./UserDto.model";

export class ModelPermissionUserDto extends BaseDto {

    public value: null | boolean;

    public subAttribute: string;

    public actionPermission: ActionPermissionDto;
    public modelPermission: ModelPermissionDto;
    public user: UserDto;


    constructor() {
        super();

        this.value = null;
        this.subAttribute = '';
        this.actionPermission = new ActionPermissionDto();
        this.modelPermission = new ModelPermissionDto();
        this.user = new UserDto();

    }
}


