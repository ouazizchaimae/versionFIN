import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import TypeClientAdminTabNavigation from '../../../../../../navigation/admin/referentiel/TypeClientAdminTabNavigation';

const TypeClientAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <TypeClientAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default TypeClientAdmin;
